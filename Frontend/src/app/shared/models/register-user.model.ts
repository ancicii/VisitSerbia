export class RegisterUser {
  password: string = "";
  firstName: string = "";
  lastName: string = "";
  email: string = "";
  maritalStatus: string = "";
  years: number;
  numberOfKids: number;
  verified: boolean = false;


  constructor(password: string, firstName: string, lastName: string, email: string, maritalStatus: string, years: number, numberOfKids: number) {
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.maritalStatus = maritalStatus;
    this.years = years;
    this.numberOfKids = numberOfKids;
  }
}
