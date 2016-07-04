<?php
namespace Admin\Controller;
use Think\Controller;
header("Content-type: text/html; charset=utf-8");
class IndexController extends Controller {
    public function index(){
$this->show();
        /*$this->show('
<style type="text/css">*
{ padding: 0; margin: 0; } 
div{ padding: 4px 48px;} 
body{ background: #fff; font-family: "微软雅黑"; color: #333;font-size:24px}
 h1{ font-size: 100px; font-weight: normal; margin-bottom: 12px; } 
p{ line-height: 1.8em; font-size: 36px } a,a:hover{color:blue;}</style><div style="padding: 24px 48px;"> <h1>:)</h1><p>欢迎使用 <b>ThinkPHP</b>！</p><br/>版本 V{$Think.version}</div><script type="text/javascript" src="http://ad.topthink.com/Public/static/client.js"></script><thinkad id="ad_55e75dfae343f5a1"></thinkad><script type="text/javascript" src="http://tajs.qq.com/stats?sId=9347272" charset="UTF-8"></script>','utf-8');
 */

  }
    public function search(){
	$key = $_POST["key"];
	redirect('http://localhost/phpmyadmin/smallTest/shop/?g=Home&c=Goods&a=showlist&search='.$key);
    }
    public function login(){
	$name =  I('get.username');
	$password = I('post.password', '', 'htmlspecialchars');
	$this->assign('name', $name);
	$this->assign('password', $password);
	$this->display();
    //$db = new mysqli( $dbhost, $username, $userpass, $dbdatabase);
    // 构建写入的数据数组
    //$user = D('User');
    //$name = I('post.username');
    //$password = md5($_POST['password']);
    //$user->where('username=\''.$name.'\' and password=\''.$password.'\'');
    //$info = $user->select();
	//$na = 'wqeqwe';
	// $this->assign('na', $na);
    //$this->assign('name', $name);
    //$this->assign('password', $password);
	//$this->display();
    //$result = $db->query("select * from user where username = ".$data["username"]."&& password = ".$data["password"].";");
    //$array  = $result->fetch_row();
    //if ($array[0]==''||$array[0]==null)
	//	$this->show("AD/guanggao");
//$this->show("AD/guanggao");

}
}
?>
