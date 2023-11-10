package org.zerock.jdbcex.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.jdbcex.dao.TodoDAO;
import org.zerock.jdbcex.domain.TodoVO;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum TodoService {
    INSTANCE;
    //TodoService 는 ModelMapper 와 TodoDAO를 이용할 수 있도록 수성
    private TodoDAO dao;
    private ModelMapper modelMapper;
    TodoService(){
        dao = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();


    }
    // register(DTO)를 넣으면
    //dao.insert(VO) 가 실행됨 .
    //넣어주니까 타입 형태를 VO
    public void register(TodoDTO todoDTO) throws  Exception{
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        log.info(todoVO);
        dao.insert(todoVO); //int 를 반환하므로 이를 이용해서 예외처리도 가능
    }
    //
    public List<TodoDTO> listAll()throws  Exception{
        List<TodoVO> voList = dao.selectAll();
        log.info("voList.................");
        log.info(voList);
        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo,TodoDTO.class))
                .collect(Collectors.toList());
        // 단순히 출력해주니까 dto 타입으로 변환 후 출력
        return  dtoList; }


    public TodoDTO get(Long tno)throws Exception {

        log.info("tno: " + tno);
        TodoVO todoVO = dao.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
        //vo타입으로 출련된 값을 매핑으로 DTO로 변환 후 출력
        return todoDTO;
    }

    public void remove(Long tno)throws Exception {
    // 삭제는 데이터 자체에 변환 없이 단순 삭제니 DTO를 사용할 이유가 없음
        log.info("tno: " + tno);
        dao.deleteOne(tno);
    }
    public void modify(TodoDTO todoDTO)throws Exception {

        log.info("todoDTO: " + todoDTO );

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        dao.updateOne(todoVO);
        //입력 후 다시 vo로 저장

    }

}
