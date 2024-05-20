package kr.co.littleriders.backend.common.fixture;


import kr.co.littleriders.backend.application.dto.request.AcademyTerminalRegisterRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@AllArgsConstructor
@Getter
public enum TerminalFixture {

    A("a3ea5563-ddca-43cb-aea8-abd307dc8a0a"),
    B("0c722402-b2db-4bef-be13-70c8021d7424"),
    C("91a51d60-14f4-4d85-b3e2-84587d19fc9b"),
    D("d6f75f4d-0d0d-4c90-81d0-47c5f65cdcf2"),
    E("51c00c58-0413-4038-8b14-99035880c34d"),
    F("05b33cd3-ccd2-491d-bb15-509da5f3284c"),
    G("7666f88c-b266-4b21-b2f9-b0efaa0b41b2"),
    H("7112b25d-f7c7-4cfc-b9d0-d319f5a3ddc3"),
    I("b57db941-b9f1-4998-8373-68ea4405b1b1"),
    J("ab587614-f514-4f3e-9213-53ac76d6b9a5"),
    K("fe3cecfe-5809-47c4-afc2-0ff407443325"),
    L("142c6dfd-3e98-48af-924a-d7fcf87390dd"),
    M("c26b636d-2069-4608-98c5-b649439f894f"),
    N("1fc523af-66ab-4c77-8c24-75412fcec78e"),
    O("0377e4b4-e49b-4ce9-bd0c-e72d2ae29ee4"),
    P("79c6af0a-1808-4fe4-bbfe-de58a1af65d5"),
    Q("a7b0b179-a3d2-41e6-8bb8-a9fb59a5503e"),
    R("f7ad7695-68d2-4cb6-975b-077ad75a5717"),
    S("7bbb0861-1561-4cc0-b367-42b2eab46ee0"),
    T("989ed6fc-ee4c-4108-a29d-ef3a77f91ac2");


    private String terminalNumber;

    public Terminal toTerminal(Academy academy) {
        return Terminal.of(
                academy,
                terminalNumber
        );
    }

    public AcademyTerminalRegisterRequest toAcademyTerminalRegisterRequest() {
        return new AcademyTerminalRegisterRequest(terminalNumber);
    }


}
