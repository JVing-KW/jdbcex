// Source code is decompiled from a .class file using FernFlower decompiler.
package org.zerock.jdbcex.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.service.TodoService;

@WebServlet(
        name = "todoListController",
        value = {"/todo/list"}
)
public class TodoListController extends HttpServlet {
    private static final Logger log = LogManager.getLogger(TodoListController.class);
    private TodoService todoService;

    public TodoListController() {
        this.todoService = TodoService.INSTANCE;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("todo list..................");

        try {
            List<TodoDTO> dtoList = this.todoService.listAll();
            req.setAttribute("dtoList", dtoList);
            req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
        } catch (Exception var4) {
            log.error(var4.getMessage());
            throw new ServletException("list error");
        }
    }
}
