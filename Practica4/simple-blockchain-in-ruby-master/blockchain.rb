###########################
#	Improved version of "Build your own blockchain from scratch in 20 lines of Ruby!"	
#		from https://github.com/openblockchains/awesome-blockchains/tree/master/blockchain.rb
#  
#   and inspired by
#     Let's Build the Tiniest Blockchain In Less Than 50 Lines of Python by Gerald Nash
#     see https://medium.com/crypto-currently/lets-build-the-tiniest-blockchain-e70965a248b
#
#	Now, Blockchain with prompt transactions, transactions counter for each block, 
#											 ledger, proof of work and dynamic variable name. 
#
#	This Blockchain can be set as a loop for infinite using of the Blockchain.
#
#
#  to run use:
#    $ ruby ./blockchain.rb
#
#
#

require 'digest'    							# For hash checksum digest function SHA256
require 'pp'        							# For pp => pretty printer
# require 'pry'                     # For on the fly debugging
require_relative 'block'					# class Block
require_relative 'transaction'		# method Transactions

LEDGER = []

#####
## Blockchain building, one block at a time.
##  This will create a first block with fake transactions
## 	and then prompt user for transactions informations and set it in a new block.
## 	
## Each block can take multiple transaction
## 	when a user has finish to add transaction, 
##  the block is added to the blockchain and writen in the ledger


def create_first_block
  transactions = [
    { from: "Dutchgrown", to: "Vincent", what: "Tulip Bloemendaal Sunset", qty: 10 },
    { from: "Keukenhof", to: "Anne", what: "Tulip Semper Augustus", qty: 7 }
  ]
  b0 = Block.first(transactions)
  LEDGER << b0
  pp b0
  p "============================"
  add_block(b0)
end
	
	
	
def add_block(previous_block)
  loop do
    block = Block.next(previous_block, get_transactions_data)
    LEDGER << block
    p "============================"
    pp block
    p "============================"
    break if no_more_transactions? # Detener el bucle si no hay más transacciones disponibles
    previous_block = block
  end
end

def no_more_transactions?
  # Devuelve true si no hay más transacciones, de lo contrario, devuelve false
end

def launcher
  puts "==========================="
  puts ""
  puts "Welcome to Simple Blockchain In Ruby!"
  puts ""
  sleep 1.5
  puts "This program was created by Anthony Amar for educational purposes"
  puts ""
  sleep 1.5
  puts "Wait for the genesis (the first block of the blockchain)"
  puts ""
  10.times do
    print "."
    sleep 0.5
  end
  puts ""
  puts ""
  puts "==========================="
  create_first_block
end

launcher
