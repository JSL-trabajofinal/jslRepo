import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { CrearPlanillaPageRoutingModule } from './crear-planilla-routing.module';

import { CrearPlanillaPage } from './crear-planilla.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ReactiveFormsModule,
    CrearPlanillaPageRoutingModule
  ],
  declarations: [CrearPlanillaPage]
})
export class CrearPlanillaPageModule {}
