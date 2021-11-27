import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-cuadrillas',
  templateUrl: './cuadrillas.page.html',
  styleUrls: ['./cuadrillas.page.scss'],
})
export class CuadrillasPage implements OnInit {
  datosCuadrilla;
  id_Cuadrilla;
  
  constructor(private http: HttpClient, private router:Router) { }

  public contenidoArray: any = null;
  public contenidoArrayTecnico: any = null;
  url = environment.urlServidor;

  ngOnInit() {
    this.listarCuadrillas();
  }

  listarCuadrillas(){
    const httpOptions = {
      headers: new HttpHeaders({
        Accept: 'application/json;profile=urn:org.apache.isis/v1',
        Authorization: 'Basic YWRtaW46cGFzcw==',
      }),
    };
    const URL = this.url + '/restful/services/simple.SimpleCuadrillaMenu/actions/listAll/invoke';
    this.http.get(URL, httpOptions).subscribe((resultados: Array<any>) =>  {
      var array = resultados;
      array.pop();
      this.contenidoArray = array;
    });
  }

  obtieneCuadrilla(idCuadrilla){
    console.log(idCuadrilla);
    this.router.navigate(['/cuadrilla-detalle', { id_Cuadrilla: idCuadrilla}]);
  }

}
