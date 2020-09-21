package uk.co.real_technologies.fragments

import uk.co.real_technologies.repo.database.AccountEntity

interface FragmentCommunicator {
    fun onItemClickListener(categoryName: String?)
    fun onItemAccountClickListener(accountEntity: AccountEntity?)
}