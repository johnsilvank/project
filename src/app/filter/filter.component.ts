import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../common/services/user.service'
import { forkJoin } from 'rxjs';
import { NgbCalendar, ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import * as moment from 'moment';
import { DatePipe } from '@angular/common';
import { __core_private_testing_placeholder__ } from '@angular/core/testing';
import { NgbTimeStruct } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {

  private datepickerVisibility: boolean = true;
  time: NgbTimeStruct = { hour: 13, minute: 30, second: 30 };
  modelObject: any;
  rightBoxId: HTMLElement;
  orderForm: FormGroup;
  updateItem: FormGroup;
  farmValues: FormArray;

  queryName: any;
  arrayItems: any[] = [];
  savedQuery: any[] = [];
  queryList: any[];
  queryFeatch: any[];
  postResponse: any;
  updateQuerySchema: any[];
  rightBoxData: any[]
  savedQueryName: any;
  getFieldData: any = {};
  leftDropDownListItem: string;
  getId: any;
  listDataResponse: any;
  show: boolean = false;
  trueValue: boolean = false;
  deletedisabled: boolean = true
  selectedQueryIndex: number;
  dateSelect: boolean = true;
  buttonHideShow: boolean = false;
  SaveQueryButtonShow: boolean = false;

  selectedValue1: string;
  selectedValue2: string;
  selectedValue3: string;
  selectedValue4: string;
  formatedFromDate: string
  formatedToDate: string;

  closeResult: string;



  constructor(public _formBuilder: FormBuilder,
    public userService:
      UserService, calendar:
      NgbCalendar,
    public datepipe: DatePipe,
    private modalService: NgbModal) { };

  ngOnInit() {
    /* Getting Left Drop Down List- Open  */
    this.userService.listData().subscribe((res) => {
      this.listDataResponse = res;
      for (let l = 0; l < this.listDataResponse.length; l++) {
        if (this.listDataResponse[l] === "hour") {
          this.listDataResponse.splice(l, 1);
        } if (this.listDataResponse[l] === "zone_min_power") {
          this.listDataResponse.splice(l, 1);
        } if (this.listDataResponse[l] === "zone_max_power") {
          this.listDataResponse.splice(l, 1);
        }
      }
    }), (err) => {
      alert('Error Occure' + err);
    };
    /* Getting Left Drop Down List- Close  */

    /* Form Control - Open*/
    this.orderForm = this._formBuilder.group({
      items: this._formBuilder.array([this.createItem()])
    });
    this.queryFeatch = JSON.parse(localStorage.getItem('savedQueryData'));
    /* Form Control - Close */
  }


  /* Form Array Creation - Open */
  createItem() {
    return this._formBuilder.group({
      property: [''],
      property_TypeValue: ['']
    });
  }


  addItemArray() {
    if (this.leftDropDownListItem == 'dt') {
      this.datepickerVisibility = false;
      this.addItem.push(this.createItem());
    } else {
      this.addItem.push(this.createItem());
    }

  }

  get addItem() {
    this.farmValues = <FormArray>this.orderForm.get('items');
    return this.farmValues;
  }
  /* Form Array Creation - Close */





  /* All Date Control - Open */
  isTimePickerEnabled = true;
  daterangepickerOptions = {
    startDate: null,
    endDate: null,
    format: "MM-DD-YYYY HH:mm:ss",
    minDate: moment().add(-2, 'months').format("MM-DD-YYYY HH:mm:ss"),
    maxDate: moment().add(2, 'months').format("MM-DD-YYYY HH:mm:ss"),
    inactiveBeforeStart: true,
    autoApply: true,
    showRanges: true,
    preDefinedRanges: [{
      name: 'Day After tomorrow',
      value: {
        start: moment().add(2, 'days'),
        end: moment().add(2, 'days'),
      }
    }, {
      name: 'Today',
      value: {
        start: moment(),
        end: moment(),
      }
    }, {
      name: 'Tomorrow',
      value: {
        start: moment().add(1, 'days'),
        end: moment().add(1, 'days'),
      }
    }, {
      name: 'This week',
      value: {
        start: moment(),
        end: moment().add(7, 'days'),
      }
    }],
    singleCalendar: false,
    displayFormat: 'MM-DD-YYYY HH:mm:ss',
    position: 'left',
    disabled: false,
    noDefaultRangeSelected: true,
    timePicker: {
      minuteInterval: 5,
      twentyFourHourFormat: true
    },
    disableBeforeStart: true
  }

  rangeSelected(data) {

    /*Start Date UTC Convesion - Open*/
    let startDate = moment(data.start._d).format("ddd MMM DD YYYY hh:mm:ss");
    let newDateStart = new Date(startDate);
    let UtcDateTimeForStart = newDateStart.toUTCString();
    let dateStringStart = this.datepipe.transform(UtcDateTimeForStart, 'MM-dd-yyyy');
    let timeStringStart: any[] = UtcDateTimeForStart.split(" ");
    /*Start Date UTC Convesion - Close*/

    /*End Date UTC Convesion - Open*/

    let lastDate = moment(data.end._d).format("ddd MMM DD YYYY hh:mm:ss")
    let newDateEnd = new Date(lastDate)
    let UtcDateTimeForEnd = newDateEnd.toUTCString();
    let dateStringEnd = this.datepipe.transform(UtcDateTimeForEnd, 'MM-dd-yyyy');
    let timeStringEnd: any[] = UtcDateTimeForEnd.split(" ");
    /*End Date UTC Convesion - Close*/

    /*Final Date Strings - Open*/
    this.formatedFromDate = `${dateStringStart} ${timeStringStart[4]}`
    this.formatedToDate = `${dateStringEnd} ${timeStringEnd[4]}`
    /*Final Date Strings - close*/
  }
  singleCalendar(event) {
    this.daterangepickerOptions.singleCalendar = event.target.checked;
  }
  autoApply(event) {
    this.daterangepickerOptions.autoApply = event.target.checked;
  }
  inactiveBeforeStart(event) {
    this.daterangepickerOptions.inactiveBeforeStart = event.target.checked;
  }
  showRanges(event) {
    this.daterangepickerOptions.showRanges = event.target.checked;
  }
  setTimePicker(event) {
    this.isTimePickerEnabled = event.target.checked;
    this.daterangepickerOptions.timePicker = event.target.checked ? {
      minuteInterval: 5,
      twentyFourHourFormat: this.daterangepickerOptions.timePicker && !!this.daterangepickerOptions.timePicker.twentyFourHourFormat
    } : null;
  }
  setPosition() {

  }
  prettyPrintJSON(object) {
    return JSON.stringify(object, null, '  ')
  }

  /* All Date Control - Close */


  open(content) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  idFeatingFun(a) {
    return a.id;
  }

  /* Clicking On Left Drop Down Getting Right Side Drop Down  - Open */
  getQuery(event, i): void {
    this.buttonHideShow = true;
    this.leftDropDownListItem = event.target.value;
    this.dateSelect = true;
    this.userService.getType(this.leftDropDownListItem, this.selectedValue1, this.selectedValue4, this.selectedValue3, this.selectedValue2)
      .subscribe((res) => {
        this.getFieldData['getFieldData' + i] = res;
      }, (err) => {
        alert('server Time Out');
        console.log(err);
      }
      )
  }



  selectValue(event, i) {

    if (i >= 1) {
      if (this.leftDropDownListItem === 'site_name') {
        this.selectedValue1 = 'site_name=' + event.target.value;
      } else if (this.leftDropDownListItem === 'zone_name') {
        this.selectedValue2 = 'zone_name=' + event.target.value;
      } else if (this.leftDropDownListItem === 'floor_name') {
        this.selectedValue3 = 'floor_name=' + event.target.value;
      } else if (this.leftDropDownListItem === 'building_name') {
        this.selectedValue4 = 'building_name=' + event.target.value;
      }
    }
  }

  /* Clicking On Left Drop Down Getting Right Side Drop Down  - Open */

  dateArray: Array<string> = ['dt', 'two', 'three'];

  onSubmit() {
    this.arrayItems = this.orderForm.value.items;
    for (let i = 0; i < this.arrayItems.length; i++) {
      if (this.dateArray.indexOf(this.arrayItems[i].property) > -1) {
        let dbDate = this.formatedFromDate + ',' + this.formatedToDate;
        this.arrayItems[i].property_TypeValue = dbDate;
      }
    }
    /* Posting Selected Multiple Fields Queries */

    let newData = JSON.stringify(this.arrayItems);
    this.userService.postData(newData).subscribe((response) => {
      this.postResponse = response;
      /* Condition for No result Found */
      if (this.postResponse.length === 0) {
        this.show = true;
      } else {
        this.show = false;
      }
    }, (err) => {
      console.log(err)
    });
    this.modelObject = {
      "prop_details": JSON.stringify(this.arrayItems)
    }
  }

  changeDateFormatForList(val) {
    let ar = val.split('-');
    let year = ar[0];
    let month = ar[1];
    let day = ar[2];
    return month + '-' + day + '-' + year
  }


  /* Save Query In Local Storage - Open */


  queyName(event): void {
    this.queryName = event.target.value;
    return event.target.value;
  }

  saveTextValue(a) {
    return a.value
  }

  saveQuery(): void {
    let idVAlue: HTMLElement = document.getElementById('saveId');
    this.arrayItems = this.orderForm.value.items;
    if (this.saveTextValue(idVAlue) == []) {
      alert("Please Enter Query Name");
    } else {
      if (this.trueValue === true) {
        if (this.saveTextValue(idVAlue) === this.savedQueryName) {
          let getLocalStorage: any[] = JSON.parse(localStorage.getItem('savedQueryData'));
          for (let i = 0; i < getLocalStorage.length; i++) {
            if (getLocalStorage[i].queryName === this.savedQueryName) {
              getLocalStorage.splice(i, 1);
              this.updateDateToArray()
              getLocalStorage.push({ 'queryName': this.saveTextValue(idVAlue), 'prop_details': this.arrayItems });
              localStorage.removeItem('savedQueryData');
              localStorage.setItem('savedQueryData', JSON.stringify(getLocalStorage));
              break;
            }
          }
          alert('Query Is Saved')
          this.onSubmit();
        }
        else {
          this.updateDateToArray()
          this.queryList = JSON.parse(localStorage.getItem('savedQueryData'));
          this.queryList.push({ 'queryName': this.saveTextValue(idVAlue), 'prop_details': this.arrayItems });
          localStorage.setItem('savedQueryData', JSON.stringify(this.queryList));
          alert('Query Is Saved');
          this.onSubmit();
        }
      } else {
        if (localStorage.getItem('savedQueryData') === null) {
          this.updateDateToArray()
          this.savedQuery.push({ 'queryName': this.saveTextValue(idVAlue), 'prop_details': this.arrayItems });
          localStorage.setItem("savedQueryData", JSON.stringify(this.savedQuery));
          alert('Query Is Saved');
          this.onSubmit();
        } else {
          this.queryList = JSON.parse(localStorage.getItem('savedQueryData'));

          const checkRoleExistence = roleParam => this.queryList.some(({ queryName }) => queryName == roleParam)
          if (checkRoleExistence(this.saveTextValue(idVAlue))) {
            alert("Name Already Exists");
          } else {
            this.updateDateToArray()
            this.queryList.push({ 'queryName': this.saveTextValue(idVAlue), 'prop_details': this.arrayItems });
            localStorage.setItem('savedQueryData', JSON.stringify(this.queryList));
            alert('Query Is Saved ');
            this.onSubmit();
          }
        }
      }
      this.queryFeatch = JSON.parse(localStorage.getItem('savedQueryData'));
    }
  }

  updateDateToArray() {
    for (let i = 0; i < this.arrayItems.length; i++) {
      if (this.dateArray.indexOf(this.arrayItems[i].property) > -1) {
        let dbDate = this.formatedFromDate + ',' + this.formatedToDate;
        this.arrayItems[i].property_TypeValue = dbDate;
      }
    }
  }


  updateQuery(event: any, q, i) {
    delete this.postResponse;
    this.selectedQueryIndex = i;
    this.show = false
    this.trueValue = true;
    this.orderForm = this._formBuilder.group({
      items: this._formBuilder.array([])
    });
    if (this.trueValue) {
      this.savedQueryName = q.queryName;
    }
    let promises = [];
    for (let x = 0; x < q.prop_details.length; x++) {
      let call = this.userService.updateQueryList(q.prop_details[x].property);
      promises.push(call);
    }
    forkJoin(promises).subscribe(
      (res) => {
        this.rightBoxData = res;
        for (let p = 0; p < this.rightBoxData.length; p++) {
          this.getFieldData['getFieldData' + p] = this.rightBoxData[p];
          this.updateItem = this._formBuilder.group({
            property: [q.prop_details[p].property],
            property_TypeValue: [q.prop_details[p].property_TypeValue]
          });
          this.addItem.push(this.updateItem);
        }
      }, (err) => {
        alert("server Time Out");
      });
    this.updateQuerySchema = q.prop_details;
    this.userService.postData(this.updateQuerySchema).subscribe((response) => {
      this.postResponse = response;
      if (this.postResponse.length === 0) {
        this.show = true;
      } else {
        this.show = false;
      }
    }, (err) => {
      console.log(err)
    
    });
    this.deletedisabled = false;
  }
  /* Save Query In Local Storage And Up Date Query  - Close */


  /* Remove And Deletion - Open */

  removeInputField(i): void {
    this.addItem.removeAt(-1);
    this.postResponse = [];
    if (this.addItem.value.length == 0) {
      this.buttonHideShow = false;
    }

  }

  resetForm() {
    this.buttonHideShow = false;
    delete this.savedQueryName;
    this.orderForm.setControl('items', this._formBuilder.array([]));
    this.addItem.reset();
    this.trueValue = false;
    this.orderForm = this._formBuilder.group({
      items: this._formBuilder.array([this.createItem()])
    });
    this.getFieldData = {};
    this.deletedisabled = true;
    this.postResponse = [];
  }



  deleteQuery(event: any) {
    this.savedQueryName = " ";
    if (confirm('Are you sure to delete ' + this.savedQueryName)) {
      let deleteQueryKey: any[] = JSON.parse(localStorage.getItem('savedQueryData'));
      if (this.selectedQueryIndex !== -1) {
        deleteQueryKey.splice(this.selectedQueryIndex, 1);
      }
      localStorage.setItem('savedQueryData', JSON.stringify(deleteQueryKey));
      this.queryFeatch = JSON.parse(localStorage.getItem('savedQueryData'));
      this.resetForm();
    }
  }
  /* Remove And Deletion - Open */

}
