package dev.patricksilva.crud.models.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import dev.patricksilva.crud.models.shared.PeopleDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String card;
    private String cpf;
    private String phone;
    private String cardMonth;
    private String cardYear;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String gender;
    private String dateOfBirth;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * UserDetails constructor.
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param authorities
     * @param card
     * @param cpf
     * @param phone
     * @param cardMonth
     * @param cardYear
     * @param address
     * @param city
     * @param state
     * @param zipCode
     * @param country
     * @param gender
     * @param dateOfBirth
     */
    public UserDetailsImpl(String id, String firstName, String lastName, String email, String password,
                           Collection<? extends GrantedAuthority> authorities, String card, String cpf, String phone,
                           String cardMonth, String cardYear, String address, String city, String state,
                           String zipCode, String country, String gender, String dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.card = card;
        this.cpf = cpf;
        this.phone = phone;
        this.cardMonth = cardMonth;
        this.cardYear = cardYear;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public static UserDetailsImpl build(PeopleDTO peopleDTO) {
        List<GrantedAuthority> authorities = peopleDTO.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role)) // Usando a string diretamente
                .collect(Collectors.toList());

        // Aqui, você deve garantir que a senha de PeopleDTO já esteja criptografada ao ser passada
        return new UserDetailsImpl(peopleDTO.getId(), peopleDTO.getFirstName(), peopleDTO.getLastName(),
                peopleDTO.getEmail(), peopleDTO.getPassword(), authorities, peopleDTO.getCard(),
                peopleDTO.getCpf(), peopleDTO.getPhone(), peopleDTO.getCardMonth(), peopleDTO.getCardYear(),
                peopleDTO.getAddress(), peopleDTO.getCity(), peopleDTO.getState(), peopleDTO.getZipCode(),
                peopleDTO.getCountry(), peopleDTO.getGender(), peopleDTO.getDateOfBirth());
    }

    // Getters and Setters
    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardMonth() {
        return cardMonth;
    }

    public void setCardMonth(String cardMonth) {
        this.cardMonth = cardMonth;
    }

    public String getCardYear() {
        return cardYear;
    }

    public void setCardYear(String cardYear) {
        this.cardYear = cardYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    // End Getters and Setters


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorities, card, cardMonth, cardYear, cpf, email, firstName, id, lastName, password,
                phone, address, city, state, zipCode, country, gender, dateOfBirth);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        UserDetailsImpl other = (UserDetailsImpl) obj;
        return Objects.equals(authorities, other.authorities) && Objects.equals(card, other.card)
                && Objects.equals(cardMonth, other.cardMonth) && Objects.equals(cardYear, other.cardYear)
                && Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
                && Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
                && Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
                && Objects.equals(phone, other.phone) && Objects.equals(address, other.address)
                && Objects.equals(city, other.city) && Objects.equals(state, other.state)
                && Objects.equals(zipCode, other.zipCode) && Objects.equals(country, other.country)
                && Objects.equals(gender, other.gender) && Objects.equals(dateOfBirth, other.dateOfBirth);
    }
}
