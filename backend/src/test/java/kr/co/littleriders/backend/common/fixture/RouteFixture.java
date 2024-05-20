package kr.co.littleriders.backend.common.fixture;


import kr.co.littleriders.backend.application.dto.request.RouteRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.route.entity.Route;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public enum RouteFixture {



    A("A 코스"),
    B("B 코스"),
    C("C 코스"),
    D("D 코스"),
    E("E 코스"),
    F("F 코스"),
    G("G 코스"),
    H("H 코스"),
    I("I 코스"),
    J("J 코스"),
    K("K 코스"),
    L("L 코스"),
    M("M 코스"),
    N("N 코스"),
    O("O 코스"),
    P("P 코스"),
    Q("Q 코스"),
    R("R 코스"),
    S("S 코스"),
    T("T 코스"),
    U("U 코스"),
    V("V 코스"),
    W("W 코스"),
    X("X 코스"),
    Y("Y 코스"),
    Z("Z 코스");

   private String name;

    public Route toRoute(Academy academy,String type){
        return Route.of(academy, name ,type );
    }


    public RouteRequest toRouteRequest(String type){
        return new RouteRequest(name,type);
    }




}
