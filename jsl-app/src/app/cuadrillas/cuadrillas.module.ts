import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { CuadrillasPageRoutingModule } from './cuadrillas-routing.module';

import { CuadrillasPage } from './cuadrillas.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    CuadrillasPageRoutingModule
  ],
  declarations: [CuadrillasPage]
})
export class CuadrillasPageModule {}
