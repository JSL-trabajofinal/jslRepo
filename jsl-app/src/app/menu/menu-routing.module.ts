import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MenuPage } from './menu.page';

const routes: Routes = [
  {
    path: '',
    component: MenuPage,
    children: [
      {
        path: 'home',
        loadChildren: () =>
          import('../home/home.module').then(
            (m) => m.HomePageModule),
      },
      {
        path: 'reclamos',
        loadChildren: () =>
          import('../reclamos/reclamos.module').then(
            (m) => m.ReclamosPageModule
          ),
      },
      {
        path: 'cuadrillas',
        loadChildren: () =>
          import('../cuadrillas/cuadrillas.module').then(
            (m) => m.CuadrillasPageModule
          ),
      },

      {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full',
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MenuPageRoutingModule {}
