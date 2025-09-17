import { Pageable } from "./pageable";


export interface Page<T> {
  content: T[],
  pageable: Pageable,
  last: boolean,
  totalPages: number,
  totalElements: number,
  size: number;
}