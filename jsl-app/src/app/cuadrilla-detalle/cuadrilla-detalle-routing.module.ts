import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CuadrillaDetallePage } from './cuadrilla-detalle.page';

const routes: Routes = [
  {
    path: '',
    component: CuadrillaDetallePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CuadrillaDetallePageRoutingModule {}
