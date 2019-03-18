import { BrowserModule } from '@angular/platform-browser';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { DatePipe } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { HeaderComponent } from './common/header/header.component';
import { SidePanelComponent } from './common/side-panel/side-panel.component';

import { FilterComponent } from './filter/filter.component';
import { UserService } from '../app/common/services/user.service'
import { DaterangepickerModule } from 'angular-2-daterangepicker';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidePanelComponent,
    FilterComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,   
    DaterangepickerModule
    
  ],
  providers: [UserService,DatePipe,],
  bootstrap: [AppComponent],
  //exports:[DaterangepickerModule]
})
export class AppModule { }
