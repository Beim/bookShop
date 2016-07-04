<?php
namespace Home\Controller;
use Think\Controller;
header("content-type:text/html;charset=utf-8");
	class GoodsController extends Controller{
	function showlist()
{
		$search    =  $_GET['search'];
		$book = D('Book');
		//var_dump($book);
		$book->where('bookname like '.'\'%'.$search.'%\'');
		$info = $book->select();
		$count=$book->where('bookname like '.'\'%'.$search.'%\'')->count();
		if ($info == null)
			$this->display('sorry');
		else{
		$this->assign('js_arr',json_encode($info));
		$this->assign('count', $count);
		$this->display();
	}
}
public function login()
{
    	$name = $_POST['name'];
	$password = $_POST['password'];
	$user = D('User');
	$user->where("username=\"".$name."\" and password=\"".$password."\"");
	
	if($user->select())
	{
		$flag = "1";
	}
	else
	{
		$flag = "0";
		$fail = "1";
	}	
	$this->assign('name', $name);
	$this->assign('go', $go);
	$this->assign('password', $password);
	$this->assign('flag', $flag);
	$this->assign('fail', $fail);
	$this->display("index");
	
	//redirect('http://localhost/phpmyadmin/smallTest/shop/?g=Home&c=Goods&a=showlist&search='.$key);
}
}
?>
