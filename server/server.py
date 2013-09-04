import sys
import BaseHTTPServer
from SimpleHTTPServer import SimpleHTTPRequestHandler
import random
from urlparse import urlparse

#example call - http://192.168.1.74:8000/?cmd=0001&key=foobar
class ServerHandler(BaseHTTPServer.BaseHTTPRequestHandler):

    def do_GET(self):
        print self.path
        query = urlparse(self.path).query
        params = {}
        for param in query.split('&'):
            (key, value) = param.split("=")
            params[key] = value
        print params


def start_server():
    HandlerClass = SimpleHTTPRequestHandler
    ServerClass = BaseHTTPServer.HTTPServer
    Protocol = "HTTP/1.0"

    if sys.argv[1:]:
        port = int(sys.argv[1])
    else:
        port = 8000
    server_address = ('', port)

    HandlerClass.protocol_version = Protocol
    httpd = ServerClass(server_address, ServerHandler)

    sa = httpd.socket.getsockname()
    print "Serving HTTP on", sa[0], "port", sa[1], "..."
    while True:
        httpd.handle_request()

def generate_code():
    code = str(random.randint(0,9)) + str(random.randint(0,9)) + str(random.randint(0,9)) + str(random.randint(0,9))
    print code

if __name__ == "__main__":
    generate_code()
    start_server()
