function formValidation() {
    namecheck();
    contactcheck();
    emailcheck();
    passwordcheck();
  }
  function loginValidation() {}
  
  function namecheck() {
    let name = document.getElementById("name").value;
    let p1 = /^[a-zA-Z]+ [a-zA-Z]+$/;
    if (name == "") alert("Enter a valid name");
    else if (!p1.test(name)) {
      alert("Invalid name");
      document.getElementById("name").value = "";
      document.getElementById("name").focus();
    } else return true;
  }
  
  function contactcheck() {
    let contact = document.getElementById("contactNumber").value;
    let p2 = /^[0-9]+$/;
  
    if (contact == "") {
      alert("Enter a valid contact number.");
    } else if (contact.length != 10) {
      alert("Please enter 10 digit contact number.");
      document.getElementById("contactNumber").value = "";
      document.getElementById("contactNumber").focus();
    } else if (!p2.test(contact)) {
      alert("Invalid contact number.");
      document.getElementById("contactNumber").value = "";
      document.getElementById("contactNumber");
    }
  }
  
  function emailcheck() {
    let email = document.getElementById("email-address").value;
    let p3 =
      /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
  
    if (email == "") {
      alert("Enter a valid Email.");
    } else if (!p3.test(email)) {
      alert("Invalid Email.");
      document.getElementById("email-address").value = "";
      document.getElementById("email-address").focus();
    }
  }
  function passwordcheck() {
    let pass = document.getElementById("password").value;
    let confrmpass = document.getElementById("confirmPassword").value;
    let p4 = /^[a-zA-Z0-9!@#$%^&*]{6,16}$/;
  
    if (pass == "" || confrmpass == "") {
      alert("Enter a password");
    } else if (pass !== confrmpass) {
      alert("Password does not match.");
      document.getElementById("password").value = "";
      document.getElementById("password").focus();
      document.getElementById("confirmPassword").value = "";
      document.getElementById("confirmPassword").focus();
    } else if (!p4.test(pass)) {
      alert(
        "Password should contain atleast one digit and one special character."
      );
      document.getElementById("password").value = "";
      document.getElementById("password").focus();
      document.getElementById("confirmPassword").value = "";
      document.getElementById("confirmPassword").focus();
    }
  }
  