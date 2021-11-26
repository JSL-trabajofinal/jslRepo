import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-reclamos',
  templateUrl: './reclamos.page.html',
  styleUrls: ['./reclamos.page.scss'],
})
export class ReclamosPage implements OnInit {
  resultadosArraytemp: any;
  constructor(private http: HttpClient, private router: Router) {}

  public contenidoArray: any = null;
  public resultadosArrayFiltrado = [];
  url = environment.urlServidor;
  

  ngOnInit() {
    this.listarReclamos();
  }
  listarReclamos() {
    this.resultadosArrayFiltrado = [];
    const httpOptions = {
      headers: new HttpHeaders({
        Accept: 'application/json;profile=urn:org.apache.isis/v1',
        Authorization: 'Basic YWRtaW46cGFzcw==',
      }),
    };
    const URL = this.url + '/restful/services/simple.SimpleReclamoMenu/actions/listAll/invoke';
    this.http.get(URL, httpOptions).subscribe((resultados: Array<any>) => {
      this.contenidoArray = resultados;
      this.resultadosArraytemp = this.contenidoArray;


      const largoArray = this.resultadosArraytemp.length;

      for (var i = 0; i < largoArray; ) {
        if (this.resultadosArraytemp[i].hasOwnProperty('estado')) {
          if (this.resultadosArraytemp[i].estado != 'Cerrado' && this.resultadosArraytemp[i].estado != 'Anulado') {
            this.resultadosArrayFiltrado.push(this.contenidoArray[i]);
          }
        }
        i = i + 1;
      }
      this.resultadosArrayFiltrado.sort(((a, b) => a.nroReclamo - b.nroReclamo));
      this.contenidoArray = this.resultadosArrayFiltrado;
    });
  }
  obtieneReclamo(idReclamo) {
    console.log(idReclamo);
    this.router.navigate(['/reclamo-detalle', { id_Reclamo: idReclamo }]);
  }
}

