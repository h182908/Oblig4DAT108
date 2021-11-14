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

  sjekkPassordLike() {
    if (true) {
      console.log('passord er ikke like');
    } else {
      console.log('passord er like');
    }
  }

  run() {
    pwd.addEventListener('input', this.sjekkPassordStyrke);
    // pwd.addEventListener('input', this.sjekkPassordLike);
    // rep.addEventListener('input', this.sjekkPassordLike);
  }
}

const pwd = document.getElementById('passord');
const rep = document.getElementById('passordRepetert');

const controller = new FORMController(pwd, rep);

controller.run();
