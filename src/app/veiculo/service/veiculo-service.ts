import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Page } from '../../model/page';
import { Veiculo } from '../model/veiculo';

@Injectable({
  providedIn: 'root'
})
export class VeiculoService {

  readonly API = 'http://localhost:8080/veiculos';


  constructor(private http: HttpClient) { }

  public findAll():Observable<Page<Veiculo>>{
    return this.http.get<Page<Veiculo>>(this.API);
  }

  public findById(id: number):Observable<Veiculo>{
    return this.http.get<Veiculo>(`${this.API}/${id}`);
  }

  public put(id: number, veiculo: Veiculo):Observable<Veiculo>{
    return this.http.put<Veiculo>(`${this.API}/${id}`, veiculo);
  }

  public create(veiculo: Veiculo):Observable<Veiculo>{
    return this.http.post<Veiculo>(this.API, veiculo);
  } 
  public delete(id: number):Observable<void>{
    return this.http.delete<void>(`${this.API}/${id}`);
  }
}