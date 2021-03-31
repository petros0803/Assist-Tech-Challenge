using System;

namespace HotelAutomation.Application.Common.Interfaces.Repositories
{
    interface IBaseRepository<TEntity>
    {
        TEntity Insert(TEntity user);

        TEntity Delete(TEntity user);

        TEntity Update(TEntity user);

        TEntity GetById(Guid id);
    }
}
