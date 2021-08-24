这一讲是“进阶篇”的第一讲，从今天开始，我会用连续的 8 讲的篇幅来详细解析 HTTP 协议里的各种头字段，</br>
包括定义、功能、使用方式、注意事项等等。学完了这些课程，你就可以完全掌握 HTTP 协议。</br>
在前面的“基础篇”里我们了解了 HTTP 报文的结构，知道一个 HTTP 报文是由“header+body”组成的。</br>
但那时我们主要研究的是 header，没有涉及到 body。所以，“进阶篇”的第一讲就从 HTTP 的 body 谈起。</br>
**数据类型与编码**</br>
在 TCP/IP 协议栈里，传输数据基本上都是“header+body”的格式。但 TCP、UDP 因为是传输层的协议，</br>
它们不会关心 body 数据是什么，只要把数据发送到对方就算是完成了任务。</br>
而 HTTP 协议则不同，它是应用层的协议，数据到达之后工作只能说是完成了一半，还必须要告诉上层应用这是什么数据才行，否则上层应用就会“不知所措</br>
你可以设想一下，假如 HTTP 没有告知数据类型的功能，服务器把“一大坨”数据发给了浏览器，浏览器看到的是一个“黑盒子”，这时候该怎么办呢？</br>
当然，它可以“猜”。因为很多数据都是有固定格式的，所以通过检查数据的前几个字节也许就能知道这是个 GIF 图片、或者是个 MP3 音乐文件，</br>
但这种方式无疑十分低效，而且有很大几率会检查不出来文件类型。</br>
幸运的是，早在 HTTP 协议诞生之前就已经有了针对这种问题的解决方案，不过它是用在电子邮件系统里的，</br>
让电子邮件可以发送 ASCII 码以外的任意数据，方案的名字叫做“多用途互联网邮件扩展”</br>
(Multipurpose Internet Mail Extensions)， 简称为 MIME。</br>
这里简单列举一下在 HTTP 里经常遇到的几个类别：text：即文本格式的可读数据，我们最熟悉的应该就是 text/html 了，</br>
表示超文本文档，此外还有纯文本 text/plain、样式表 text/css 等。image：即图像文件，有 image/gif、image/jpeg、image/png 等。</br>
audio/video：音频和视频数据，例如 audio/mpeg、video/mp4 等。application：数据格式不固定，可能是文本也可能是二进制，</br>
必须由上层应用程序来解释。常见的有 application/json，application/javascript、application/pdf 等，</br>
另外，如果实在是不知道数据是什么类型，像刚才说的“黑盒”，就会是 application/octet-stream，即不透明的二进制数据。</br>
但仅有 MIME type 还不够，因为 HTTP 在传输时为了节约带宽，有时候还会压缩数据，为了不要让浏览器继续“猜”，</br>
还需要有一个“Encoding type”，告诉数据是用的什么编码格式，这样对方才能正确解压缩，还原出原始的数据。</br>
比起 MIME type 来说，Encoding type 就少了很多，常用的只有下面三种：gzip：GNU zip 压缩格式，</br>
也是互联网上最流行的压缩格式；deflate：zlib（deflate）压缩格式，流行程度仅次于 gzip；br一种专门为 HTTP 优化的新压缩算法(Brotli)</br>
gzip</br>
response header : </br>
HTTP/1.1 200 OK























