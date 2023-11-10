package org.zerock.jdbcex.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.zerock.jdbcex.domain.TodoVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
public class TodoDAO {

    public String getTime() throws Exception{
    // 중복된 try catch문 제거
    // 무슨 일이 일어나든 상관없이 close 메서드를 호출하여 주석을 추가한 변수 선언이 정리되도록 합니다.
        // 범위 끝까지 지역 변수 선언 다음에 오는 모든 명령문을 finallty 작업으로 리소스를 닫는 try 블록으로 래핑하여 구현됩니다
      @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();{
            @Cleanup      PreparedStatement preparedStatement = connection.prepareStatement("select now()");
            @Cleanup      ResultSet resultSet = preparedStatement.executeQuery();
        }
        // 여기 어떻게 변환?
        return null;
    }
    public void insert(TodoVO vo) throws Exception {
        String sql = "insert into tbl_todo (title, dueDate, finished) values (?, ?, ?)";

        Connection connection = ConnectionUtil.INSTANCE.getConnection();


            PreparedStatement preparedStatement = connection.prepareStatement(sql);


                preparedStatement.setString(1, vo.getTitle());
                preparedStatement.setDate(2, Date.valueOf(vo.getDueDate()));
                preparedStatement.setBoolean(3, vo.isFinished());
                preparedStatement.executeUpdate();
        }

        public List<TodoVO> selectAll()throws Exception{
        String sql = "select * from tbl_todo";
        //ConnectionUtil의 싱글턴 인스턴스를 사용하여 데이터베이스 연결을 얻는 코드입니다.
        Connection connection = ConnectionUtil.INSTANCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TodoVO> list = new ArrayList();
            while(resultSet.next()) {
                TodoVO vo = TodoVO.builder().tno(resultSet.getLong("tno"))
                        .title(resultSet.getString("title"))
                        .dueDate(resultSet.getDate("dueDate").toLocalDate())
                        .finished(resultSet.getBoolean("finished")).build();
                list.add(vo);
            }
            //Builder 패턴은 객체를 생성하는 과정을 단계별로 수행할 수 있도록 하는 디자인 패턴입니다. Builder 패턴을 사용하면 객체 생성 코드가 더 읽기 쉬워지고,
            // 선택적 속성을 설정하지 않아도 객체를 생성할 수 있으며, 객체 생성할 때 유효성 검사를 수행할 수 있습니다.

            // TodoVO.builder().



            return list;}
        public TodoVO selectOne(Long tno)throws Exception{
        String sql = "select * from tbl_todo where tno = ?";
        @Cleanup    Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup    PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

            TodoVO vo = TodoVO.builder().tno(resultSet.getLong("tno"))
                    .title(resultSet.getString("title"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished")).build();

        return vo; }

        // 글 삭제
        public void deleteOne(Long tno)throws Exception{
        String sql = "delete from tbl_tode where tno = ?";
            @Cleanup    Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup    PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,tno);
            preparedStatement.executeUpdate();

         }
    public void updateOne(TodoVO todoVO) throws Exception {
        String sql = "update tbl_todo set title =?, dueDate = ?, finished = ? where tno =?";
        @Cleanup   Connection connection = ConnectionUtil.INSTANCE.getConnection();
         @Cleanup   PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, todoVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
        preparedStatement.setBoolean(3, todoVO.isFinished());
        preparedStatement.setLong(4, todoVO.getTno());
         preparedStatement.executeUpdate();



            }


        }










