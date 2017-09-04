/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { FjepaTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { PhotolistDetailComponent } from '../../../../../../main/webapp/app/entities/photolist/photolist-detail.component';
import { PhotolistService } from '../../../../../../main/webapp/app/entities/photolist/photolist.service';
import { Photolist } from '../../../../../../main/webapp/app/entities/photolist/photolist.model';

describe('Component Tests', () => {

    describe('Photolist Management Detail Component', () => {
        let comp: PhotolistDetailComponent;
        let fixture: ComponentFixture<PhotolistDetailComponent>;
        let service: PhotolistService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [FjepaTestModule],
                declarations: [PhotolistDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    PhotolistService,
                    JhiEventManager
                ]
            }).overrideTemplate(PhotolistDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PhotolistDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PhotolistService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Photolist(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.photolist).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
