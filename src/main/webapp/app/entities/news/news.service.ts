import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { News } from './news.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class NewsService {

    private resourceUrl = 'api/news';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(news: News): Observable<News> {
        const copy = this.convert(news);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(news: News): Observable<News> {
        const copy = this.convert(news);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<News> {
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
        entity.createTime = this.dateUtils
            .convertDateTimeFromServer(entity.createTime);
        entity.updateTime = this.dateUtils
            .convertDateTimeFromServer(entity.updateTime);
        entity.expDate = this.dateUtils
            .convertDateTimeFromServer(entity.expDate);
    }

    private convert(news: News): News {
        const copy: News = Object.assign({}, news);

        copy.createTime = this.dateUtils.toDate(news.createTime);

        copy.updateTime = this.dateUtils.toDate(news.updateTime);

        copy.expDate = this.dateUtils.toDate(news.expDate);
        return copy;
    }
}
