import { BaseEntity } from './../../shared';

export class News implements BaseEntity {
    constructor(
        public id?: number,
        public title?: string,
        public summary?: string,
        public author?: string,
        public editor?: string,
        public pic?: string,
        public content?: any,
        public createTime?: any,
        public updateTime?: any,
        public isTop?: number,
        public ctype?: number,
        public expDate?: any,
    ) {
    }
}
