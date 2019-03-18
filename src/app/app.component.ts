import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  closeResult: string;

  ngOnInit() {
    $(document).ready(function () {
      const menuIcon = $('.mobile-menu-icon');
      menuIcon.click(function () {
        $(this).toggleClass('active');
        $('.left-sidebar').toggleClass('active');
      });
    });
  }
  constructor() { }
}


