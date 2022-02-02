import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServicioUrlService } from '../servicios/servicio-url.service';

@Component({
  selector: 'app-cuadrillas',
  templateUrl: './cuadrillas.page.html',
  styleUrls: ['./cuadrillas.page.scss'],
})
export class CuadrillasPage implements OnInit {
  datosCuadrilla;
  id_Cuadrilla;
  
  constructor(private http: HttpClient, private router:Router,
    private servicioUrl: ServicioUrlService) { }

  public contenidoArray: any = null;
  public contenidoArrayTecnico: any = null;
  url: any;
  ngOnInit() {
    this.servicioUrl.$getObjectSource.subscribe(data  =>  {
      console.log(data)
      this.url = data;
     }).unsubscribe();
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
