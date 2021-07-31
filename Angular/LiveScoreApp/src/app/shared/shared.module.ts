import { NgModule } from '@angular/core';
import { MaterialModule } from "./material/material.module";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [],
  imports: [
    HttpClientModule,
    MaterialModule
  ],
  exports: [FormsModule,ReactiveFormsModule,MaterialModule],
  providers:[]
})
export class SharedModule { }