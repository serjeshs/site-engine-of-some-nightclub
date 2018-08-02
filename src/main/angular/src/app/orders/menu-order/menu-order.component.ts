
import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';


@Component({
  selector: 'app-menu-order',
  templateUrl: './menu-order.component.html',
  styleUrls: ['./menu-order.component.css']
})
export class MenuOrderComponent implements OnInit {
  isLinear = false;
  menuFormGroup: FormGroup;
  orderDetailsFormGroup: FormGroup;

  constructor(private _formBuilder: FormBuilder) {}

  ngOnInit() {
    this.menuFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.orderDetailsFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
  }
}
