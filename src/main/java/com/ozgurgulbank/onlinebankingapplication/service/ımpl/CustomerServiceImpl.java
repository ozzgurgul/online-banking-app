package com.ozgurgulbank.onlinebankingapplication.service.ımpl;

import com.ozgurgulbank.onlinebankingapplication.entity.account.ColorfulAccount;
import com.ozgurgulbank.onlinebankingapplication.entity.account.DepositAccount;
import com.ozgurgulbank.onlinebankingapplication.entity.card.CreditCard;
import com.ozgurgulbank.onlinebankingapplication.entity.customer.Address;
import com.ozgurgulbank.onlinebankingapplication.entity.customer.Customer;
import com.ozgurgulbank.onlinebankingapplication.entity.customer.Info;
import com.ozgurgulbank.onlinebankingapplication.exception.ResourceNotFoundException;
import com.ozgurgulbank.onlinebankingapplication.repository.CustomerRepository;
import com.ozgurgulbank.onlinebankingapplication.request.customer.CreateCustomerRequest;
import com.ozgurgulbank.onlinebankingapplication.request.customer.update.UpdateAddressRequest;
import com.ozgurgulbank.onlinebankingapplication.request.customer.update.UpdateInfoRequest;
import com.ozgurgulbank.onlinebankingapplication.service.ICustomerService;
import com.ozgurgulbank.onlinebankingapplication.validation.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final Checker checker = new Checker();

    @Override
    public ResponseEntity<Object> createCustomer(CreateCustomerRequest createCustomerRequest) {

       boolean name = checker.nameChecker(createCustomerRequest.getCreateCustomer().getName());
       boolean surname = checker.nameChecker(createCustomerRequest.getCreateCustomer().getSurname());
       boolean identification = checker.identificationNoChecker(createCustomerRequest.getCreateCustomer().getIdNo());
       boolean email = checker.emailChecker(createCustomerRequest.getRequestInfo().getEmail());
       boolean phoneNumber = checker.phoneNoChecker(createCustomerRequest.getRequestInfo().getPhoneNo());
       boolean zipcode = checker.zipCodeChecker(createCustomerRequest.getRequestAddress().getZipcode());

        if(!name){

           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lütfen geçerli bir isim giriniz.");
        }
        if(!surname){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lütfen geçerli bir soyisim giriniz");
        }
        if(!identification){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lütfen geçerli T.C. kimlik numarası giriniz");
        }
        if(!email){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lütfen geçerli bir email giriniz");
        }
        if(!phoneNumber){
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lütfen geçerli bir telefon numarası giriniz");
        }
        if(!zipcode){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lütfen geçerli bir zipcode giriniz");
        }

        Customer customer = customerRepository.findByIdNo(createCustomerRequest.getCreateCustomer().getIdNo());
        if(customer != null){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Girdiğiniz T.C. Kimlik numarasına sahip bir müşteri zaten kayıtlı.");
        }

        customer = new Customer();

        customer.setName(createCustomerRequest.getCreateCustomer().getName());
        customer.setSurname(createCustomerRequest.getCreateCustomer().getSurname());
        customer.setBirthDate(createCustomerRequest.getCreateCustomer().getBirthDate());
        customer.setIdNo(createCustomerRequest.getCreateCustomer().getIdNo());



        Address address = new Address(0L, createCustomerRequest.getRequestAddress().getCity(),
                createCustomerRequest.getRequestAddress().getStreet(),
                createCustomerRequest.getRequestAddress().getZipcode(),
                customer);

        Info info = new Info(0L, createCustomerRequest.getRequestInfo().getEmail(), createCustomerRequest.getRequestInfo().getPhoneNo(),customer);

        customer.setAddress(address);
        customer.setInfo(info);

        customerRepository.save(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body("Müşteri tanımladı.");
    }

    @Override
    public List<Customer> findAll() {

        return customerRepository.findAll();
    }

    @Override
    public Customer findByIdentificationNumber(String identificationNumber) {

        if(customerRepository.findByIdNo(identificationNumber)==null){
            throw new ResourceNotFoundException("Müşteri","kimlik numarası",identificationNumber);
        }

        return customerRepository.findByIdNo(identificationNumber);
    }

    @Override
    public ResponseEntity<Object> deleteByIdentificationNumber(String identificationNumber) {

        Customer customer = customerRepository.findByIdNo(identificationNumber);

        if(customer == null) {

            throw new ResourceNotFoundException("Müşteri","kimlik numarası",identificationNumber);

        }
            List<ColorfulAccount> colorfulAccounts = customer.getColorfulAccount();

            for(int i=0; i<colorfulAccounts.size(); i++)
            {
                if(colorfulAccounts.get(i).getBalance() != 0){
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Hesapta bakiye olduğu için kayıt silinemiyor");
                }
            }

         DepositAccount depositAccount = customer.getDepositAccount();
            if(depositAccount !=null && depositAccount.getBalance()!=0){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Hesapta bakiye olduğu için müşteri kaydı silinemiyor");
            }

            CreditCard creditCard = customer.getCreditCard();
            if(creditCard !=null && creditCard.getCurrentLimit()!=creditCard.getTotalLimit()){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Kredi kartı borcu olduğu için müşteri kaydı silinemiyor");
            }

            customerRepository.delete(customer);
            return ResponseEntity.status(HttpStatus.OK).body("Müşteri silindi");

        }

    @Override
    public ResponseEntity<Object> updateAddress(UpdateAddressRequest request, String idNo) {

        boolean zipcode = checker.zipCodeChecker(request.getZipcode());
        if(!zipcode){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lütfen geçerli bir zipcode giriniz");
        }

        Customer customer = customerRepository.findByIdNo(idNo);
        if(customer==null){
            throw new ResourceNotFoundException("Müşteri","kimlik numarası",idNo);
        }

        customer.getAddress().setCity(request.getCity());
        customer.getAddress().setStreet(request.getCity());
        customer.getAddress().setZipcode(request.getZipcode());

        customerRepository.save(customer);

        return ResponseEntity.status(HttpStatus.OK).body("Adres güncellendi");
    }

    @Override
    public ResponseEntity<Object> updateInfo(UpdateInfoRequest request,String idNo) {
        boolean email = checker.emailChecker(request.getEmail());
        boolean phoneNumber = checker.phoneNoChecker(request.getPhoneNo());

        if(!email){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lütfen geçerli bir email giriniz");
        }
        if(!phoneNumber){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lütfen geçerli bir telefon numarası giriniz");
        }

        Customer customer = customerRepository.findByIdNo(idNo);
        if(customer==null){
            throw new ResourceNotFoundException("Müşteri","kimlik numarası",idNo);
        }

        customer.getInfo().setEmail(request.getEmail());
        customer.getInfo().setPhoneNo(request.getPhoneNo());

        customerRepository.save(customer);

        return ResponseEntity.status(HttpStatus.OK).body("İletişim bilgileri güncellendi");

    }

}




