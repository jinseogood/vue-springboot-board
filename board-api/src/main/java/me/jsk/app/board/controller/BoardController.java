package me.jsk.app.board.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.jsk.app.board.domain.BoardVO;
import me.jsk.app.board.domain.ReplyVO;
import me.jsk.app.board.service.BoardService;

@CrossOrigin
@Controller
@EnableAutoConfiguration
@RequestMapping("/board")
public class BoardController {

  @Autowired
  private BoardService boardService;
  
  /**
   * 게시글 목록 조회
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @GetMapping(value="/list")
  public HashMap<String, Object> selectBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String schType = request.getParameter("schType");
    String schVal = request.getParameter("schVal");
    int rows = Integer.parseInt(request.getParameter("rows"));
    int page = Integer.parseInt(request.getParameter("page"));
    String[] sort = URLDecoder.decode(request.getParameter("sort"), "UTF-8").split(",");

    BoardVO vo = new BoardVO();
    vo.setSchType(schType);
    vo.setSchVal(schVal);
    vo.setStartNo(((page * rows) - rows) + 1);
    vo.setEndNo(page * rows);
    vo.setSort(sort);

    List<BoardVO> result = boardService.selectBoardList(vo);

    int total = 0;

    if(result.size() > 0) {
      total = boardService.selectBoardListCount(vo);
    }

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("data", result);
    map.put("total", total);

    return map;
  }

  /**
   * 게시글 상세 조회
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @GetMapping(value = "/detail")
  public BoardVO selectBoardDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String docNo = request.getParameter("docNo");

    BoardVO vo = new BoardVO();
    vo.setDocNo(Integer.parseInt(docNo));

    // 조회수 증가
    boardService.increaseViewCount(vo);

    BoardVO result = boardService.selectBoardDetail(vo);

    return result;
  }

  /**
   * 게시글 작성
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/insert")
  public int insertBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String title = request.getParameter("title");
    String content = request.getParameter("content");

    BoardVO vo = new BoardVO();
    vo.setTitle(title);
    vo.setContent(content);
    // TODO 로그인 사용자로 작성자 받기
    vo.setWriter("Test Writer");

    int result = 0; // 신규 게시글 번호
    
    int rows = boardService.insertBoard(vo);

    if(rows > 0) {
      result = vo.getDocNo();
    }

    return result;
  }

  /**
   * 게시글 수정
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/update")
  public int updateBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String docNo = request.getParameter("docNo");
    String title = request.getParameter("title");
    String content = request.getParameter("content");

    BoardVO vo = new BoardVO();
    vo.setDocNo(Integer.parseInt(docNo));
    vo.setTitle(title);
    vo.setContent(content);

    int rows = boardService.updateBoard(vo);

    return rows;
  }

  /**
   * 게시글 삭제
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/delete")
  public int deleteBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String docNo = request.getParameter("docNo");

    BoardVO vo = new BoardVO();
    vo.setDocNo(Integer.parseInt(docNo));

    // 댓글 유무 확인 후 같이 삭제
    ReplyVO replyVO = new ReplyVO();
    replyVO.setDocNo(Integer.parseInt(docNo));

    List<ReplyVO> result = boardService.selectReplyList(replyVO);

    if(result.size() > 0) {
      boardService.deleteReply(replyVO);
    }

    int rows = boardService.deleteBoard(vo);

    return rows;
  }

  /**
   * 댓글 조회
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @GetMapping(value = "/reply/list")
  public List<ReplyVO> selectReplyList(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String docNo = request.getParameter("docNo");

    ReplyVO vo = new ReplyVO();
    vo.setDocNo(Integer.parseInt(docNo));

    List<ReplyVO> result = boardService.selectReplyList(vo);

    return result;
  }

  /**
   * 댓글 작성
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/reply/insert")
  public int insertReply(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String docNo = request.getParameter("docNo");
    String comment = request.getParameter("comment");

    ReplyVO vo = new ReplyVO();
    vo.setDocNo(Integer.parseInt(docNo));
    vo.setWriter("Test Writer");
    vo.setContent(comment);

    int rows = boardService.insertReply(vo);

    return rows;
  }

  /**
   * 댓글 수정
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/reply/update")
  public int updateReply(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String replyNo = request.getParameter("replyNo");
    String docNo = request.getParameter("docNo");
    String comment = request.getParameter("comment");

    ReplyVO vo = new ReplyVO();
    vo.setReplyNo(Integer.parseInt(replyNo));
    vo.setDocNo(Integer.parseInt(docNo));
    vo.setContent(comment);

    int rows = boardService.updateReply(vo);

    return rows;
  }

  /**
   * 댓글 삭제
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/reply/delete")
  public int deleteReply(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String replyNo = request.getParameter("replyNo");
    String docNo = request.getParameter("docNo");

    ReplyVO vo = new ReplyVO();
    vo.setReplyNo(Integer.parseInt(replyNo));
    vo.setDocNo(Integer.parseInt(docNo));

    int rows = boardService.deleteReply(vo);

    return rows;
  }
  
}
