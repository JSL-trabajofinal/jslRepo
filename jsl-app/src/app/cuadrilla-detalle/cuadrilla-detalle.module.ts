import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { CuadrillaDetallePageRoutingModule } from './cuadrilla-detalle-routing.module';

import { CuadrillaDetallePage } from './cuadrilla-detalle.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    CuadrillaDetallePageRoutingModule
  ],
  declarations: [CuadrillaDetallePage]
})
export class CuadrillaDetallePageModule {}
