import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DetalleCuadrillaPage } from './detalle-cuadrilla.page';

const routes: Routes = [
  {
    path: '',
    component: DetalleCuadrillaPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DetalleCuadrillaPageRoutingModule {}
