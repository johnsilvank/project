import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private _http: HttpClient) { };
  public _url = "http://10.131.73.194:5000/excelfore/api/v1.0/resources/getAllInfo_new";
  public _getUrl = "http://10.131.73.194:5000/excelfore/api/v1.0/resources/getDropdown?field_type="
  public _listItems = "http://10.131.73.194:5000/excelfore/api/v1.0/resources/getProperties";

  /* Post Query And featching response*/
  postData(data) {
    var reqHeader = new HttpHeaders({ 'Content-Type': 'application/json', "Access-Control-Allow-Origin": '*', "Access-Control-Allow-Methods": 'POST,PATCH,DELETE,PUT,OPTIONS', "Access-Control-Allow-Headers": 'Origin, Content-Type, X-Auth-Token, content-type' });

    return this._http.post(this._url, data, { headers: reqHeader });
  }

  /*Getting Left DropDown*/
  listData() {
    return this._http.get(this._listItems);
  }

  /*Update Query*/
  updateQueryList(prop): Observable<any> {
    return this._http.get(this._getUrl + `${prop}`);
  }

  /*Getting Right Drop Down On selecting parameter from Left Drop Down */
  getType(propValues1, propValue2, propValue3, propValue4, propValue5) {
    return this._http.get(this._getUrl + `${propValues1}&${propValue2}&${propValue3}&${propValue4}&${propValue5}`);

  }
}
