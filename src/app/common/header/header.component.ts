import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
    name : any = "Nischal"
  constructor() { }

  ngOnInit() {
  }
  refresh(): void {
    window.location.reload();
}
}
