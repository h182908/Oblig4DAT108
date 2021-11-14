class PasswordValidator {
  constructor() {
    this.re_lower = /[a-z]+/;
    this.re_upper = /[A-Z]+/;
    this.re_number = /[1-9]+/;
  }

  validate(pwd) {
    return (
      pwd.length < 12 ||
      !this.re_lower.test(pwd) ||
      !this.re_upper.test(pwd) ||
      !this.re_number.test(pwd)
    );
  }
}

class FORMController {
  constructor(pwd, rep) {
    this.pwd = pwd;
    this.rep = rep;
    this.sjekkPassordLike = this.sjekkPassordLike.bind(this);
  }

  sjekkPassordStyrke(event) {
    const validity = event.target.validity;
    const validator = new PasswordValidator();

    if (validity.valid && validator.validate(event.target.value)) {
      event.target.classList.add('mediumPassword');
    } else {
      event.target.classList.remove('mediumPassword');
    }
  }

  sjekk() {}

  sjekkPassordLike() {
    console.log(this.pwd);
    if (pwd.value != rep.value) {
      this.rep.setCustomValidity('Repetert passord er feil!');
    } else {
      this.rep.setCustomValidity('');
    }
  }

  run() {
    this.pwd.addEventListener('input', this.sjekkPassordStyrke);
    this.pwd.addEventListener('input', this.sjekkPassordLike);
    this.rep.addEventListener('input', this.sjekkPassordLike);
  }
}

const pwd = document.getElementById('passord');
const rep = document.getElementById('passordRepetert');

const controller = new FORMController(pwd, rep);

controller.run();
