function loginValidation() {
    emailcheck();
    passwordcheck();
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
    let p4 = /^[a-zA-Z0-9!@#$%^&*]{6,16}$/;
  
    if (pass == "") {
      alert("Enter a password");
    } else if (!p4.test(pass)) {
      alert(
        "Password should contain atleast one digit and one special character."
      );
      document.getElementById("password").value = "";
      document.getElementById("password").focus();
    }
  }
  