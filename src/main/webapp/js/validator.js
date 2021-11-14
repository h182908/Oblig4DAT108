class PasswordValidator {
  constructor() {
    this.re_lower = /[a-z]+/;
    this.re_upper = /[A-Z]+/;
    this.re_number = /[1-9]+/;
    this.validate = this.validate.bind(this);
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
    this.validator = new PasswordValidator();
    this.sjekkPassordStyrke = this.sjekkPassordStyrke.bind(this);
    this.sjekkPassordLike = this.sjekkPassordLike.bind(this);
  }

  sjekkPassordStyrke(event) {
    const validity = event.target.validity;

    if (validity.valid && this.validator.validate(event.target.value)) {
      event.target.classList.add('mediumPassword');
    } else {
      event.target.classList.remove('mediumPassword');
    }
  }

  sjekkPassordLike() {
    if (this.pwd.value != this.rep.value) {
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
