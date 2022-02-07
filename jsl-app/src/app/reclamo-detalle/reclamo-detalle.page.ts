import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { ServicioUrlService } from '../servicios/servicio-url.service';

@Component({
  selector: 'app-reclamo-detalle',
  templateUrl: './reclamo-detalle.page.html',
  styleUrls: ['./reclamo-detalle.page.scss'],
})
export class ReclamoDetallePage implements OnInit {
  id_Reclamo;
  datosReclamo;
  datosCuadrilla;
  param: any;
  url: any;

  constructor(
    private http: HttpClient,
    private activatedRoute: ActivatedRoute,
    private servicioUrl: ServicioUrlService
  ) {}

  ngOnInit() {
    this.servicioUrl.$getObjectSource.subscribe(data  =>  {
      console.log(data)
      this.url = data;
     }).unsubscribe();
    this.param = this.activatedRoute.snapshot.params;
    if (Object.keys(this.param).length) {
      this.detallarReclamo(this.param.id_Reclamo);
    }
  }
  detallarReclamo(id_Reclamo) {
    const httpOptions = {
      headers: new HttpHeaders({
        Accept: 'application/json;profile=urn:org.apache.isis/v1',
        Authorization: 'Basic YWRtaW46cGFzcw==',
      }),
    };
    const URL1 = this.url+'/restful/objects/simple.Reclamo/' + id_Reclamo;
    this.http.get(URL1, httpOptions).subscribe((resultados) => {
      this.datosReclamo = resultados;
    });

  }
}
