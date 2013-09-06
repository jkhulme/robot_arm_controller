import sys
import BaseHTTPServer
from SimpleHTTPServer import SimpleHTTPRequestHandler
import random

code = 0

_LOGIN = "0000";
_ROTATE_CCW = "0001";
_ROTATE_CW = "0010";
_SHOULDER_UP = "0011";
_SHOULDER_DOWN = "0100";
_ELBOW_UP = "0101";
_ELBOW_DOWN = "0110";
_WRIST_UP = "0111";
_WRIST_DOWN = "1000";
_GRIP_OPEN = "1001";
_GRIP_CLOSE = "1010";
_LIGHT_ON = "1011";

class ServerHandler(BaseHTTPServer.BaseHTTPRequestHandler):

    def do_GET(self):
        params = {}
        for param in self.path[1:].split('&'):
            print param
            (key, value) = param.split("=")
            params[key] = value
        global code
        if params['code'] == code:
            self.send_response(200)
            self.send_header('Content-type', 'text/html')
            self.execute_command(params['command'])
        else:
            self.send_response(401)
            self.send_header('WWW-Authenticate', 'Basic realm="robo_arm_app"')

        self.end_headers()
        return

    def execute_command(self, cmd):
        if cmd == _ROTATE_CCW:
            print "rotating arm ccw"
        elif cmd == _ROTATE_CW:
            print "rotating arm cw"
        elif cmd == _SHOULDER_UP:
            print "moving shoulder up"
        elif cmd == _SHOULDER_DOWN:
            print "moving shoulder down"
        elif cmd == _ELBOW_UP:
            print "moving elbow up"
        elif cmd == _ELBOW_DOWN:
            print "moving elbow down"
        elif cmd == _WRIST_UP:
            print "moving wrist up"
        elif cmd == _WRIST_DOWN:
            print "moving wrist down"
        elif cmd == _GRIP_OPEN:
            print "opening grip"
        elif cmd == _GRIP_CLOSE:
            print "closing grip"
        elif cmd == _LIGHT_ON:
            print "toggling light"

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
