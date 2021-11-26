import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { IonicModule, IonicRouteStrategy } from '@ionic/angular';

import { ReclamosPageRoutingModule } from './reclamos-routing.module';

import { ReclamosPage } from './reclamos.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule.forRoot(),
    ReclamosPageRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ],
  declarations: [ReclamosPage]
})
export class ReclamosPageModule {}
