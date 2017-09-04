import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { Photolist } from './photolist.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class PhotolistService {

    private resourceUrl = 'api/photolists';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(photolist: Photolist): Observable<Photolist> {
        const copy = this.convert(photolist);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(photolist: Photolist): Observable<Photolist> {
        const copy = this.convert(photolist);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<Photolist> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convertItemFromServer(entity: any) {
        entity.uploadTime = this.dateUtils
            .convertDateTimeFromServer(entity.uploadTime);
    }

    private convert(photolist: Photolist): Photolist {
        const copy: Photolist = Object.assign({}, photolist);

        copy.uploadTime = this.dateUtils.toDate(photolist.uploadTime);
        return copy;
    }
}
