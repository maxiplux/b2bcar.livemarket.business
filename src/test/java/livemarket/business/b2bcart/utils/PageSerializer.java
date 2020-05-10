package livemarket.business.b2bcart.utils;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
class Sorter implements Serializable {
    private Long id; // will be set when persisting
    private Boolean sorted;
    private Boolean unsorted;
    private Boolean empty;

}

@Getter
@Setter
@NoArgsConstructor
class Pageable implements Serializable {
    private Long id; // will be set when persisting
    private Sorter sort;
}

@Getter
@Setter
@NoArgsConstructor
public class PageSerializer implements Serializable {

    private Long id; // will be set when persisting

    private Boolean last = true;
    private Integer totalPages = 1;
    private Integer totalElements = 7;
    private Integer size = 20;
    private Integer number = 0;
    private Integer numberOfElements = 7;
    private Boolean first = true;
    private Boolean empty = false;
    private Sorter sort;
    private List<Object> content;

}
