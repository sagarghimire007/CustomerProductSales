var firstNameError = document.getElementById('firstName-error')
var nameError = document.getElementById('name-error')
var lastNameError = document.getElementById('lastName-error')
var emailError = document.getElementById('email-error')
var passwordError = document.getElementById('password-error')
var rePasswordError = document.getElementById('repassword-error')
var categoryNameError = document.getElementById('categoryName-error')


function validatePasswordFunction(){
    var a = document.getElementById("password").value;
    var b = document.getElementById("passwords").value;

    if(a == ""){
        passwordError.innerHTML = document.getElementById("password-error").innerHTML = "* please enter the password";
        return false;
    }

    if(b == ""){
        rePasswordError.innerHTML = document.getElementById("repassword-error").innerHTML = "* please re-enter the password";
        return false;
    }

    if(a != b){
        passwordError.style.display = 'block';
        passwordError.innerHTML = "* Password did not match ! Please try again..";
        setTimeout(function (){passwordError.style.display = 'none';}, 4000);
        return false;
    }
}

function validateCategory(){

    var categoryName = document.getElementById("categoryName").value;
    if(categoryName == ""){
        categoryNameError.innerHTML = document.getElementById("categoryName-error").innerHTML = "* Category Required";
        return false;
    }
   return true;
}


function validateFirstName(){
    var firstName = document.getElementById('firstName').value;

    if(firstName.length == 0){
        firstNameError.innerHTML = document.getElementById("firstName-error").innerHTML = "* required";
        return false;
    }
    firstNameError.innerHTML = '<i class="fa-light fa-circle-check"></i>';
    return true;
}

function validateLastName(){
    var lastName = document.getElementById('lastName').value;

    if(lastName.length == 0){
        lastNameError.innerHTML = document.getElementById("lastName-error").innerHTML = "* required";
        return false;
    }
    lastNameError.innerHTML = '<i class="fa-light fa-circle-check"></i>';
    return true;
}


function validateName(){
    var name =  document.getElementById('fullName').value;

    if(name.length == 0){
        nameError.innerHTML = "* required";
        return false;
    }
    if(!name.match(/^[A-Za-z]*\s{1}[A-Za-z]*$/)){
        nameError.innerHTML = "* full name required ( e.g James Bond )";
        return false;
    }

    nameError.innerHTML = '<i class="fa-light fa-circle-check"></i>';
    return true;
}



function validatePhone(){
    var phone =  document.getElementById('phoneNumber').value;

    if(phone.length == 0){
        phoneError.innerHTML = "* required";
        return false;
    }

    if(phone.length != 10){
        phoneError.innerHTML = "* phone number should be 10 digits"
        return false;
    }

    if(!phone.match(/^[0-9]{10}$/)){
        phoneError.innerHTML = "* only digits please";
        return false;
    }

    phoneError.innerHTML = '<i class="fa-light fa-circle-check"></i>';
    return true;
}

function validateEmail(){
    var  email = document.getElementById('username').value;

    if(email.length == 0){
        emailError.innerHTML = document.getElementById("email-error").innerHTML = "* required";
    }

    if(!email.match(/^[A-Za-a]\._\-[0-9]*[@][A-Za-z]*[\.][a-z]{2,4}]$/)) {
        emailError.innerHTML = document.getElementById("email-error").innerHTML = "* for e.g david@gmail.com";
        return false;
    }

    emailError.innerHTML = '<i class="fa-light fa-circle-check"></i>';
    return true;
}

function validateDate(){
    var  date = document.getElementById('dob').value;

    if(dob.date == null){
        emailError.innerHTML = "* date required";
        return false;
    }

    dateError.innerHTML = '<i class="fa-light fa-circle-check"></i>';
    return true;
}


function validateForm(){
    if(!validateName() || !validatePhone() || !validateEmail() || !validateDate()){
        submitError.style.display = 'block';
        submitError.innerHTML = " * Please fill the required field";
        setTimeout(function (){submitError.style.display = 'none';}, 3000);
        return false;
    }
}

function validateRegisterForm(){
    if(!validateFirstName() || !validateLastName() || !validateEmail() || !validatePasswordFunction()){
        submitError.style.display = 'block';
        submitError.innerHTML = " * Please fill the required field";
        setTimeout(function (){submitError.style.display = 'none';}, 3000);
        return false;
    }
}
