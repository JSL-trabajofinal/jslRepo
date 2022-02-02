import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ReclamoDetallePageRoutingModule } from './reclamo-detalle-routing.module';

import { ReclamoDetallePage } from './reclamo-detalle.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ReclamoDetallePageRoutingModule
  ],
  declarations: [ReclamoDetallePage]
})
export class ReclamoDetallePageModule {}
