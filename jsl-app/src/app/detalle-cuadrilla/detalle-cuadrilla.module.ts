import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { DetalleCuadrillaPageRoutingModule } from './detalle-cuadrilla-routing.module';

import { DetalleCuadrillaPage } from './detalle-cuadrilla.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    DetalleCuadrillaPageRoutingModule
  ],
  declarations: [DetalleCuadrillaPage]
})
export class DetalleCuadrillaPageModule {}
