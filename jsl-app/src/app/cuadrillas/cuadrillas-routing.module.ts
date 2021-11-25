import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CuadrillasPage } from './cuadrillas.page';

const routes: Routes = [
  {
    path: '',
    component: CuadrillasPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CuadrillasPageRoutingModule {}
