export class TripParamsModel {
  partOfSerbia: string;
  noOfPassengers: number;
  budget: number;
  startDate: Date;
  endDate: Date;

  constructor(partOfSerbia: string, noOfPassengers: number, budget: number, startDate: Date, endDate: Date) {
    this.partOfSerbia = partOfSerbia;
    this.noOfPassengers = noOfPassengers;
    this.budget = budget;
    this.startDate = startDate;
    this.endDate = endDate;
  }

}
