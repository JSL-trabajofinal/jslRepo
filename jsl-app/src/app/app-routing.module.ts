import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./tabs/tabs.module').then(m => m.TabsPageModule)
  },
  {
    path: 'home',
    loadChildren: () => import('./home/home.module').then( m => m.HomePageModule)
  },
  {
    path: 'menu',
    loadChildren: () => import('./menu/menu.module').then( m => m.MenuPageModule)
  },
  {
    path: 'login',
    loadChildren: () => import('./login/login.module').then( m => m.LoginPageModule)
  },
  {
    path: 'reclamos',
    loadChildren: () => import('./reclamos/reclamos.module').then( m => m.ReclamosPageModule)
  },
  {
    path: 'reclamo-detalle',
    loadChildren: () => import('./reclamo-detalle/reclamo-detalle.module').then( m => m.ReclamoDetallePageModule)
  },
  {
    path: 'cuadrillas',
    loadChildren: () => import('./cuadrillas/cuadrillas.module').then( m => m.CuadrillasPageModule)
  },
  {
    path: 'detalle-cuadrilla',
    loadChildren: () => import('./detalle-cuadrilla/detalle-cuadrilla.module').then( m => m.DetalleCuadrillaPageModule)
  }
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}