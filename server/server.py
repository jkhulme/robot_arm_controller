import sys
import BaseHTTPServer
from SimpleHTTPServer import SimpleHTTPRequestHandler
import random
from urlparse import urlparse

code = 0

#example call - http://192.168.1.74:8000/?cmd=0001&key=foobar
class ServerHandler(BaseHTTPServer.BaseHTTPRequestHandler):

    def do_GET(self):
        print self.path
        params = {}
        for param in self.path[1:].split('&'):
            print param
            (key, value) = param.split("=")
            params[key] = value
        print params
        global code
        print params['code']
        print code
        if params['code'] == code:
            print "correct code"
            self.send_response(200)
            self.send_header('Content-type', 'text/html')
        else:
            print "incorrect code"
            self.send_response(401)
            self.send_header('WWW-Authenticate', 'Basic realm="robo_arm_app"')

        self.end_headers()
        return


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
    global code
    code = str(random.randint(0,9)) + str(random.randint(0,9)) + str(random.randint(0,9)) + str(random.randint(0,9))
    print code

if __name__ == "__main__":
    generate_code()
    start_server()
